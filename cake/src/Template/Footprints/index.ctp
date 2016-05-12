<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('New Footprint'), ['action' => 'add']) ?></li>
    </ul>
</nav>
<div class="footprints index large-9 medium-8 columns content">
    <h3><?= __('Footprints') ?></h3>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th><?= $this->Paginator->sort('id') ?></th>
                <th><?= $this->Paginator->sort('visitor') ?></th>
                <th><?= $this->Paginator->sort('createDate') ?></th>
                <th><?= $this->Paginator->sort('delFlg') ?></th>
                <th class="actions"><?= __('Actions') ?></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($footprints as $footprint): ?>
            <tr>
                <td><?= $this->Number->format($footprint->id) ?></td>
                <td><?= $this->Number->format($footprint->visitor) ?></td>
                <td><?= h($footprint->createDate) ?></td>
                <td><?= $this->Number->format($footprint->delFlg) ?></td>
                <td class="actions">
                    <?= $this->Html->link(__('View'), ['action' => 'view', $footprint->id]) ?>
                    <?= $this->Html->link(__('Edit'), ['action' => 'edit', $footprint->id]) ?>
                    <?= $this->Form->postLink(__('Delete'), ['action' => 'delete', $footprint->id], ['confirm' => __('Are you sure you want to delete # {0}?', $footprint->id)]) ?>
                </td>
            </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    <div class="paginator">
        <ul class="pagination">
            <?= $this->Paginator->prev('< ' . __('previous')) ?>
            <?= $this->Paginator->numbers() ?>
            <?= $this->Paginator->next(__('next') . ' >') ?>
        </ul>
        <p><?= $this->Paginator->counter() ?></p>
    </div>
</div>
